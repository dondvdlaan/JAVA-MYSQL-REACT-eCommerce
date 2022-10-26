import {
    AppBar,
    Toolbar,
    IconButton,
    Badge,
    Typography
  } from "@mui/material";
import { ShoppingCart } from "@mui/icons-material";
import { Link, useLocation } from "react-router-dom";
  
import logo from "../../assets/logo-website.png";
import css from "./Navbar.module.css";
  
  interface Props{
    cartID: number;
    totalItems: number;
  }

  /**
   * The NavBar component is shown at the home and cartpages, displaying the logo, 
   * title and cart.
   */
  const Navbar = (props: Props) => {
  
    // *** Constants and variables ***
    const location = useLocation();
  
    return (
      <div>
        <AppBar position="fixed" className={css.appBar} color="inherit">
          <Toolbar>
            <Typography
              component={Link}
              to={`/${props.cartID}`}
              variant="h5"
              color="inherit"
            >
              <img
                src={logo}
                alt="Many Roads Developers"
                height="35px"
                className={css.image}
              />
            </Typography>
  
            <Typography 
            component="div"
            className={css.title}
            variant="h4"
            >
            Furniture for your new home
            </Typography>
  
            {/* {location.pathname === ("/" || `/${cartID}`) && ( */}
              <div >
                <IconButton
                  component={Link}
                  to={`/cart/${props.cartID}`}
                  aria-label="Show cart item"
                  color="inherit"
                  className={css.icon}
                >
                  <Badge badgeContent={props.totalItems} color="secondary">
                    <ShoppingCart />
                  </Badge>
                </IconButton>
              </div>
          </Toolbar>
        </AppBar>
      </div>
    );
  };
  
  export default Navbar;