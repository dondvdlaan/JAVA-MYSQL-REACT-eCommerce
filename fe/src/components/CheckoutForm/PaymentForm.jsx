import { Typography, Button, Divider } from "@mui/material";
import {
  Elements,
  CardElement,
  ElementsConsumer
} from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import Review from "./Review";

const stripePromise = loadStripe(process.env.REACT_APP_STRIPE_PUBLIC_KEY);

/**
 * Component to show the items review and requiring payment details ie card
 * number, expiration date and CVC
 */
const PaymentForm = ({
  checkoutToken,
  nextStep,
  backStep,
  shippingData,
  onCaptureCheckout
}) => {


// *** Event handlers **
  const handleSubmit = async (event, elements, stripe) => {
    event.preventDefault();

    if (!stripe || !elements) return;

    const cardElement = elements.getElement(CardElement);

    const { error, paymentMethod } = await stripe.createPaymentMethod({
      type: "card",
      card: cardElement
    });
    if (error) {
      console.log("[error]", error);
    } else {

      const orderData = {
        orderLineItems: checkoutToken.live.lineItems,
        customer: {
          custFirstName: shippingData.firstName,
          custLastName: shippingData.lastName,
          custEmail: shippingData.email
        },
        shipping: {
          addressName: "International",
          addressStreet: shippingData.address1,
          addressTownCity: shippingData.city,
          addressCountyState: shippingData.shippingSubdivision,
          addressZipCode: shippingData.zip,
          addressCountry: shippingData.shippingCountry
        },
        fulfillment: shippingData.shippingOption ,
        // fulfillment: { shipping_method: shippingData.shippingOption },
        billing: {
          addressName: 'John Doe',
          addressStreet: '234 Fake St',
          addressTownCity: 'San Francisco',
          addressCountyState: 'US-CA',
          addressZipCode: '94103',
          addressCountry: 'US'
        },
        // payment: {
        //   gateway: "stripe",
        //   stripe: {
        //     payment_method_id: paymentMethod.id
        //   }
        // },
        // pay_what_you_want: '149.99'
      };

      onCaptureCheckout(checkoutToken.id, orderData);

      nextStep();
    }
  };

  return (
    <>
      <Review checkoutToken={checkoutToken} />
      <Divider />
      <Typography variant="h6" gutterBottom style={{ margin: "20px 0" }}>
        Payment method
      </Typography>
      <Elements stripe={stripePromise}>
        <ElementsConsumer>
          {({ elements, stripe }) => (
            <form onSubmit={(e) => handleSubmit(e, elements, stripe)}>
              <CardElement />
              <br /> <br />
              <div style={{ display: "flex", justifyContent: "space-between" }}>
                <Button variant="outlined" onClick={backStep}>
                  Back
                </Button>
                <Button
                  type="submit"
                  variant="contained"
                  disabled={!stripe}
                  color="primary"
                >
                  Pay {checkoutToken.live.subTotal.formattedWithSymbol}
                </Button>
              </div>
            </form>
          )}
        </ElementsConsumer>
      </Elements>
    </>
  );
};

export default PaymentForm;
