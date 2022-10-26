import { Typography, Button, Divider } from "@mui/material";
import {
  Elements,
  CardElement,
  ElementsConsumer
} from "@stripe/react-stripe-js";
import { loadStripe, Stripe, StripeElements } from "@stripe/stripe-js";
import { FieldValues } from "react-hook-form";
import { CheckoutToken } from "../../types/CheckoutToken";
import { POWithoutCustomerRef } from "../../types/PurchaseOrder";
import Review from "./Review";


// ! at end will tell Typescript, that you will be responsible for no undefined value
const stripePromise = loadStripe(process.env.REACT_APP_STRIPE_PUBLIC_KEY !);

interface Props{
  checkoutToken: CheckoutToken;
  nextStep: () => void;
  backStep: () => void;
  shippingData: FieldValues;
  onCaptureCheckout: (a: number, b: POWithoutCustomerRef) => void;
}

/**
 * Component to show the items review and requiring payment details ie card
 * number, expiration date and CVC
 */
const PaymentForm = (props: Props) => {

  // *** Constants and variables ***
  const checkoutToken = props.checkoutToken;
  const shippingData = props.shippingData;


  // *** Event handlers **
  const handleSubmit = async  (event: React.FormEvent<HTMLFormElement>,
                               elements: StripeElements | null,
                               stripe: Stripe | null) => {
    event.preventDefault();

    if (!stripe || !elements){
      throw new Error("Stripe or StripeElements are null!")
    }

    const cardElement = elements.getElement(CardElement);

    if (!cardElement){
      throw new Error("CardElement is null!")
    }

    const { error, paymentMethod } = await stripe.createPaymentMethod({
      type: "card",
      card: cardElement
    });
    if (error) {
      console.log("[error]", error);
    } else {

      const orderData: POWithoutCustomerRef = {
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
        fulfillment: shippingData.shippingOption,
        billing: {
          addressName: 'John Doe',
          addressStreet: '234 Fake St',
          addressTownCity: 'San Francisco',
          addressCountyState: 'US-CA',
          addressZipCode: '94103',
          addressCountry: 'US'
        }
      };

      props.onCaptureCheckout(checkoutToken.id, orderData);

      props.nextStep();
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
                <Button variant="outlined" onClick={props.backStep}>
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