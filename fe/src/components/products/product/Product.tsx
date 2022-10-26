import { Card, CardMedia, CardContent, CardActions, Typography, IconButton } from '@mui/material';
import { AddShoppingCart } from "@mui/icons-material";

import css from './Product.module.css';

import { ProductInterface} from '../../../types/ProductInterface';

/**
 * Product component to show each individual product on its own card
 */
const Product = (props: { product: ProductInterface, onAddToCart: (prodID: number, y: number) =>void }) => {
    
    // *** Constants and variables ***
    const product = props.product;

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
            <IconButton aria-label="Add to Cart" onClick={() => props.onAddToCart(product.prodID, 1)}>
                <AddShoppingCart />
            </IconButton>
        </CardActions>
     </Card>
     </>   
    );
}

export default Product

