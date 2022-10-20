import { useState } from "react";
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

import css from "./Checkout.module.css";
import AddressForm from "../AddressForm";
import PaymentForm from "../PaymentForm";

import { useApi, apiSimple} from '../../../shared/Api';

const steps = ["Shipping Address", "Payment Details"];

/**
 * Component that requires shipping data and payment details from customer.
 */
const Checkout = () => {

// *** Constants and variables ***
const {cartID}                          = useParams();
const [cart, setCart]                   = useApi(`cart/${cartID}`);
const [order, setOrder]                 = useState({});
const [errorMessage, setErrorMessage]   = useState("");
const [checkoutToken, setCheckoutToken] = useApi(`generateToken/${cartID}`);
const [activeStep, setActiveStep]       = useState(0);
const [shippingData, setShippingData]   = useState({});

if(!cart) return <p>Loading cart...</p>
if(!checkoutToken) return <p>Loading checkoutToken...</p>

// *** Functions ***
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
            Thank you for your purchase, {order.customer.custFirstName}{" "}
            {order.customer.custLastName}!
          </Typography>
          <Divider className={css.divider} />
          <Typography variant="subtitle2">
            Order Ref: {order.customerReference}
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

  // Check error message before continuing
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
    
    apiSimple("POST",`newOrder/${checkoutToken.id}`, newOrder)
    .then(res=> setOrder(res.data))
    // .then(res=> console.log("newOrder",res))
    .catch(err=> {

      setErrorMessage(err);
      console.log(err)})
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
