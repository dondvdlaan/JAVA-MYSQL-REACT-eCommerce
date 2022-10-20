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

/**
 * The NavBar component is shown at the home and cartpages, displaying the logo, 
 * title and cart.
 */
const Navbar = ({cartID, totalItems }) => {

  const location = useLocation();

  return (
    <div>
      <AppBar position="fixed" className={css.appBar} color="inherit">
        <Toolbar>
          <Typography
            component={Link}
            to={`/${cartID}`}
            variant="h5"
            // className={css.appBar}
            color="inherit"
          >
            <img
              src={logo}
              alt="Many Roads Developers"
              height="35px"
              className={css.image}
            />
          </Typography>

          {/* <div className={css.grow} /> */}
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
                to={`/cart/${cartID}`}
                aria-label="Show cart item"
                color="inherit"
                className={css.icon}
              >
                <Badge badgeContent={totalItems} color="secondary">
                  <ShoppingCart />
                </Badge>
              </IconButton>
            </div>
          {/* )} */}
        </Toolbar>
      </AppBar>
    </div>
  );
};

export default Navbar;
