import { Card, CardMedia, CardContent, CardActions, Typography, IconButton } from '@mui/material';
import { AddShoppingCart } from "@mui/icons-material";

import css from './Product.module.css';

/**
 * Product component to show each individula product on its own card
 */
const Product = ({ product, onAddToCart }) => {
    
    return (
    <>
     <Card className={css.root}>
         <CardMedia 
         component="img" 
         image={product.prodImage} title={product.prodName}
          />

        <CardContent>
            <div className={css.cardContent}>

                <Typography variant="h5" gutterBottom>
                    {product.prodName}
                </Typography>

                <Typography variant="h5">
                    {product.price.formattedWithSymbol}
                </Typography>
                </div>

                <Typography dangerouslySetInnerHTML={{ __html: product.prodDescription}} variant="h6" color="textSecondary" />
        </CardContent>
        <CardActions disableSpacing className={css.cardActions}>
            <IconButton aria-label="Add to Cart" onClick={() => onAddToCart(product.prodID, 1)}>
                <AddShoppingCart />
            </IconButton>
        </CardActions>
     </Card>
     </>   
    );
}

export default Product


