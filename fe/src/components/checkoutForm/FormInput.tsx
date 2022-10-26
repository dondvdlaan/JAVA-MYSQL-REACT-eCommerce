import { TextField, Grid } from "@mui/material";
import { useFormContext, Controller } from "react-hook-form";

interface Props{
  required: boolean;
  name: string;
  label: string;
}
/**
 * This component is used in the AddressForm and is a wrapper for the
 * MUI TextField. 
 */
const FormInput = (props: Props) => {
  
  // *** Constants and variables ***
  const { control } = useFormContext();
  
  
  return (
    <Grid item xs={12} sm={6}>
      {/* Controller is a wrapper of react-hook-form to integrate the MUI TextField */}
      <Controller
        defaultValue=""
        name={props.name}
        control={control} 
        render = {({field: { onChange }})=> (
          <TextField
              onChange={onChange}
              fullWidth
              label={props.label}
              required={props.required}
          />
        )}
      />
    </Grid>
  );
};

export default FormInput;