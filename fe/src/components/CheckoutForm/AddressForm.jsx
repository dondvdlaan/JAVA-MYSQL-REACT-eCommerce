import React, { useState, useEffect } from "react";
import {
  InputLabel,
  Select,
  MenuItem,
  Button,
  Grid,
  Typography
} from "@mui/material";
import { useForm, FormProvider } from "react-hook-form";

import { Link } from "react-router-dom";
import FormInput from "./CustomTextField";
import { useApi, useApi2} from '../../shared/Api';

/**
 * This Component requires from user to input Shipping Address
 */
const AddressForm = ({ checkoutToken, next }) => {

// *** Constants and variables ***
// Retrieving shipping countries from DB
  const [countries, setCountries]                       = useApi(`countries`);
  const [shippingCountry, setShippingCountry]           = useState("");

// Retrieving provinces/states in chosen country from DB
  const [subdivisions, setSubdivisions] = 
  useApi2(`provinces/${shippingCountry}`,shippingCountry);
  
  const [shippingSubdivision, setShippingSubdivision]   = useState("");

// Retrieving options per provinces/states from DB
  const [options, setOptions] = 
  useApi2(`options/${shippingSubdivision}`,shippingSubdivision);

  const [shippingOption, setShippingOption]             = useState("");

  const methods = useForm();

  if(!countries) return <p>Loading countries...</p>

  console.log("Countries: ", countries);
  console.log("subdivisions: ", subdivisions);

  return (
    <>
      <Typography variant="h6" gutterBottom>
        Shipping Address
      </Typography>

      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit((data) =>
            next({
              ...data,
              shippingCountry,
              shippingSubdivision,
              shippingOption
            })
          )}
        >
          <Grid container spacing={3}>
            <FormInput required name="firstName" label="First Name" />
            <FormInput required name="lastName" label="Last Name" />
            <FormInput required name="address1" label="Address Line 1" />
            <FormInput required name="email" label="Email" />
            <FormInput required name="city" label="City" />
            <FormInput required name="zip" label="Zip / Postal code" />
            <Grid item xs={12} sm={6}>
              <InputLabel>Shipping Country</InputLabel>
              <Select
                value={shippingCountry}
                fullWidth
                onChange={(e) => setShippingCountry(e.target.value)}
              >
                {countries.map((country) => (
                  <MenuItem key={country.countryID} value={country.countryID}>
                    {country.countryName}
                  </MenuItem>
                ))}
              </Select>
            </Grid>

            <Grid item xs={12} sm={6}>
              <InputLabel>Shipping Subdivision</InputLabel>
              <Select
                value={shippingSubdivision}
                fullWidth
                onChange={(e) => setShippingSubdivision(e.target.value)}
              >
                {subdivisions.map((subdivision) => (
                  <MenuItem key={subdivision.provID} value={subdivision.provID}>
                    {subdivision.provName}
                  </MenuItem>
                ))}
              </Select>
            </Grid>

            <Grid item xs={12} sm={6}>
              <InputLabel>Shipping Options</InputLabel>
              <Select
                value={shippingOption}
                fullWidth
                onChange={(e) => setShippingOption(e.target.value)}
              >
                {options.map((option) => (
                  <MenuItem key={option.optionID} value={option.optionID}>
                    {option.optionDescription} - {option.price.formattedWithSymbol}
                  </MenuItem>
                ))}
              </Select>
            </Grid>
          </Grid>
          <br />

          <div style={{ display: "flex", justifyContent: "space-between" }}>
            <Button component={Link} to={`/cart/${checkoutToken.cartID}`} variant="outlined">
              Back to cart
            </Button>
            <Button type="submit" variant="contained" color="primary">
              Next
            </Button>
          </div>
        </form>
      </FormProvider>
    </>
  );
};

export default AddressForm;
