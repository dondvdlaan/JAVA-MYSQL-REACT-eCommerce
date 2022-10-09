import React, { useState, useEffect } from "react";
import { Link, useParams } from 'react-router-dom';
import {
  Paper,
  Stepper,
  Step,
  StepLabel,
  Typography,
  CircularProgress,
  Divider,
  Button
} from "@mui/material";

import { commerce } from "../../../lib/commerce";
import css from "./Checkout.module.css";
import AddressForm from "../AddressForm";
import PaymentForm from "../PaymentForm";

import { useApi} from '../../../shared/Api';

const steps = ["Shipping Address", "Payment Details"];

/**
 * Component that requires shipping data and payment details from customer.
 */
const Checkout = () => {

// *** Constants and variables ***
const {cartID}                          = useParams();
const [cart, setCart]                   = useApi(`cart/${cartID}`);
// const [cart, setCart]                   = useApi('cart');
const [order, setOrder]                 = useState({});
const [errorMessage, setErrorMessage]   = useState("");
const [checkoutToken, setCheckoutToken] = useApi(`generateToken/${cartID}`);
// const [checkoutToken, setCheckoutToken] = useState(null);
const [activeStep, setActiveStep]       = useState(0);
const [shippingData, setShippingData]   = useState({});

if(!cart) return <p>Loading cart...</p>
if(!checkoutToken) return <p>Loading checkoutToken...</p>

console.log("checkoutToken: ", checkoutToken)

// *** Functions ***
// useEffect(() => {
//   if (cart == null) return;
  
//     const generateToken = async () => {
//       try {
//         const token = await commerce.checkout.generateToken(cart.id, {
//           type: "cart"
//         });

//         setCheckoutToken(token);
//       } catch (error) {}
//     };
//     generateToken();
// }, [cart]);

const nextStep = () => setActiveStep((prevActiveStep) => prevActiveStep + 1);
const backStep = () => setActiveStep((prevActiveStep) => prevActiveStep - 1);

const next = (data) => {
    setShippingData(data);
    console.log("next data: ", data )
    nextStep();
};

let Confirmation = () =>
    order.customer ? (
      <>
        <div>
          <Typography variant="h5">
            Thank you for your purchase, {order.customer.firstname}{" "}
            {order.customer.lastname}!
          </Typography>
          <Divider className={css.divider} />
          <Typography variant="subtitle2">
            Order Ref: {order.customer_reference}
          </Typography>
        </div>
        <br />
        <Button component={Link} variant="outlined" type="button" to="/">
          Back to home
        </Button>
      </>
    ) : (
      <div className={css.spinner}>
        <CircularProgress />
      </div>
    );

const refreshCart = () => {
  commerce.cart.refresh()
  .then(newCart => setCart(newCart))
};

// Check errormessage before continuing
  if (errorMessage != "") {
    Confirmation = () => (
      <>
        <Typography variant="h5">Error: {errorMessage}</Typography>
        <br />
        <Button component={Link} variant="outlined" type="button" to="/">
          Back to home
        </Button>
      </>
    );
  }

// *** Event handlers ***
const onCaptureCheckout = async (checkoutTokenId, newOrder) => {
  try {
    console.log("onCaptureCheckout checkoutTokenId: ",checkoutTokenId );

    const incomingOrder = await commerce.checkout.capture(
      checkoutTokenId,
      newOrder
    );

    setOrder(incomingOrder);

    refreshCart();
  } catch (error) {
    console.log("onCaptureCheckout: ", error)
    setErrorMessage(error.data.error.message);
  }
};

// *** Components ***
  const Form = () =>
    activeStep === 0 ? (
      <AddressForm
        checkoutToken={checkoutToken}
        next={next}
        nextStep={nextStep}
        setShippingData={setShippingData}
      />
    ) : (
      <PaymentForm
        checkoutToken={checkoutToken}
        nextStep={nextStep}
        backStep={backStep}
        shippingData={shippingData}
        onCaptureCheckout={onCaptureCheckout}
      />
    );

  return (
    <>
      <div  />
      <main className={css.layout}>
        <Paper className={css.paper}>
          <Typography variant="h4" align="center">
            Checkout
          </Typography>
          <Stepper activeStep={activeStep} className={css.stepper}>
            {steps.map((step) => (
              <Step key={step}>
                <StepLabel>{step}</StepLabel>
              </Step>
            ))}
          </Stepper>
          {activeStep === steps.length ? (
            <Confirmation />
          ) : (
            checkoutToken && <Form />
          )}
        </Paper>
      </main>
    </>
  );
};

export default Checkout;
