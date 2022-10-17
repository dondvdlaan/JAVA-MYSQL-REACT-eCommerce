import React from "react";
import { TextField, Grid } from "@mui/material";
import { useFormContext, Controller } from "react-hook-form";

/**
 * This component is used in the AddressForm and is used as wrapper for the
 * MUI TextField. 
 */
const FormInput = ({ name, label }) => {
  
  const { control } = useFormContext();
  
  
  return (
    <Grid item xs={12} sm={6}>
      {/* Controller is a wrapper of react-hook-form to integrate the MUI TextField */}
      <Controller
        defaultValue=""
        name={name}
        control={control} 
        render = {({field: { onChange }})=> (
          <TextField
              onChange={onChange}
              fullWidth
              label={label}
              required
          />
        )}
      />
    </Grid>
  );
};

export default FormInput;
