import { Typography, List, ListItem, ListItemText } from "@mui/material";

/**
 * Component that displays the items about to be ordered with quantity and 
 * total price
 */
const Review = ({ checkoutToken }) => {

  return (
    <>
      <Typography variant="h6" gutterBottom>
        Order Summary
      </Typography>

      <List disablePadding>
        {checkoutToken.live.lineItems.map((product) => (
          <ListItem style={{ padding: "10px 0" }} key={product.productName}>
            <ListItemText
              primary={product.productName}
              secondary={`Quantity: ${product.itemQuantity}`}
            />
            <Typography variant="body2">
              {product.lineTotal.formattedWithSymbol}
            </Typography>
          </ListItem>
        ))}
        <ListItem style={{ padding: "10px 0" }}>
          <ListItemText primary="Total" />
          <Typography variant="subtitle1" style={{ fontWeight: 700 }}>
            {checkoutToken.live.subTotal.formattedWithSymbol}
          </Typography>
        </ListItem>
      </List>
    </>
  );
};

export default Review;
