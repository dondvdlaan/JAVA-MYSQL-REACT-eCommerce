import { Card, CardMedia, CardContent, CardActions, Typography, IconButton } from '@mui/material';
import { AddShoppingCart } from "@mui/icons-material";

import css from './Product.module.css';


const Product = ({ product, onAddToCart }) => {
    
    console.log("Product: ", product)

    return (
    <>
   

     <Card className={css.root}>
         <CardMedia 
         component="img" 
         image={product.prodImage} title={product.prodName}
        //  image={product.image.url} title={product.name}
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


