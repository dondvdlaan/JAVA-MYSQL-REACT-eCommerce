import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { apiSimple, useApi } from "../../shared/Api";
import axios from "axios";

export default function Test() {

  const[counter, setCounter] = useState(0);
  const [products, setProducts]   = useState();

  console.log("products: ", products)
 
  const url = "http://localhost:8080/generateToken/96";

  // *** Eventhandlers ***
const onGenerateToken=()=>{
  console.log("before axios")
  axios({method:"GET",url})
    .then(res=>{
      console.log("res: ",res)
      setProducts(res.data)
    } ) 
}


  // useEffect(() => {

  //   axios({method:"GET",url})
  //   .then(res=>setProducts(res.data))
  // },[url])















  const productss = [
    {
      prodName: "Landcruiser 71",
      prodDescription: "Mooi mooi",
      prodImage: "https://www.saharamotorsuae.com/uploads/car/21061925707DSC_6201.jpg",
      price: {
        formatted: "50000.00",
        formattedWithCode: "50000.00 Eurotjes",
        formattedWithSymbol: "€ 50000.00",
        raw: 50000.00
      },
      active: 1
    },
    {
      prodName: "Landcruiser 71",
      prodDescription: "Best in town",
      prodImage: "https://luxurycarsmanila.com.ph/wp-content/uploads/2021/06/2021-toyota-land-cruiser-lc71-3-door-2.jpg",
      price: {
        formatted: "51000.00",
        formattedWithCode: "51000.00 Eurotjes",
        formattedWithSymbol: "€ 51000.00",
        raw: 51000.00
        },
      active: 1
    },
    {
      prodName: "Landcruiser 71",
      prodDescription: "Very Good not Cheap",
      prodImage: "https://www.saharamotorsuae.com/uploads/car_image/21062695513DSC_6212.jpg",
      price: {
        formatted: "52000.00",
        formattedWithCode: "52000.00 Eurotjes",
        formattedWithSymbol: "€ 52000.00",
        raw: 52000.00
        },
      active: 1
    }
  ]
  
const path = "addProduct";
const baseUrl = "http://localhost:8080/";

// *** Eventhandlers ***
const onAddProducts=()=>{
  apiSimple("POST",path,productss[counter])
  setCounter(()=>counter + 1);
}

// const thumbnail ={
  //   title: "Toyota Landcruiser J71",
  //   url: "https://i.auto-bild.de/ir_img/2/9/2/3/7/0/3/gzgiu-7555d6e471d4ac71.jpg?impolicy=leadteaser" 
  // }
  
  // *******
  
  // const[data,setdata] = useApi("products");
  // const[data,setData] = useState([]);
  
  
  // useEffect(()=>{
    
    //   ApiSimplified("GET",path)
    //   .then(res=>{
      //     console.log("res: ",res.data);
      //     setData(res.data)
      //   }) 
      // },[]);
      
      // const x = {...data[0]}.prodImage;
      
      // console.log("x:", x)
      // data.map(x=> console.log("Product", x.prodImage));
      
      
      // const temp = data[0].prodImage;
      //const path = "retrieveCart";
      
      // const config ={
      //   method: "GET",
      //   url: `${baseUrl}${path}`
      // } ;
      
      // console.log("Config: ", config);

      // useEffect(()=>{
        
      // axios(config)
      // .then(res=> console.log("testRes: ",res.data));
      // },[]);

  return(
    <>
    
    <p>Good day!</p>
    <div>
      <p>Counter</p>
      {counter}
      <button onClick = {onAddProducts}>
        Click me!
        </button>
        <button onClick = {onGenerateToken}>
        Generate Token
        </button>
    </div>
    </>
  )
}




