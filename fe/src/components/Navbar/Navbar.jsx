import React from "react";
import {
  AppBar,
  Toolbar,
  IconButton,
  Badge,
  MenuItem,
  Menu,
  Typography
} from "@mui/material";
import { ShoppingCart } from "@mui/icons-material";
import { Link, useLocation } from "react-router-dom";

import logo from "../../assets/logo-website.png";
import css from "./Navbar.module.css";

const Navbar = ({cartID, totalItems }) => {

  // console.log("cartID: ", cartID);
  console.log("totalItems in Navbar: ", totalItems);
  
  const location = useLocation();

  return (
    <div>
      <AppBar position="fixed" className={css.appBar} color="inherit">
        <Toolbar>
          <Typography
            component={Link}
            to={`/${cartID}`}
            variant="h5"
            className={css.appBar}
            color="inherit"
          >
            <img
              src={logo}
              alt="Many Roads Developers"
              height="35px"
              className={css.image}
            />
          </Typography>

          <div className={css.grow} />
          <Typography 
          align="justify"
          variant="h4"
          >
          TOYOTA COLLECTION
          </Typography>

          {/* {location.pathname === ("/" || `/${cartID}`) && ( */}
            <div >
              <IconButton
                component={Link}
                to={`/cart/${cartID}`}
                aria-label="Show cart item"
                color="inherit"
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
