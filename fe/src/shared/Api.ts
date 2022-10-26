import axios, { AxiosPromise, AxiosResponse, Method } from "axios";
import { Dispatch, SetStateAction, useEffect, useState } from "react";

// *** Constants and variables ***
const baseUrl = "http://localhost:8080";
// local utility type
type SetState<T> = Dispatch<SetStateAction<T>>;

// *** Functions ***
/*
 * Abstracts away both needs for api calls,
 * on rendering and on events / conditions
 *
 * useBookApi, hook
 * bookApi, normal function
 *
 */

/*
 * Useful for http data as a dependency in rendering
 *
 * @param method [Method], http method
 * @param path [string], relative path to baseUrl
 * @return, Response Data
 */
export function useApi<T>(
  path: string
): [T | undefined, SetState<T | undefined>] {
  const [data, setData] = useState<T>();

  useEffect(() => {
    api("GET", path, setData);
  }, [path]);

  return [data, setData];
}

/*
 * This Api2 function was copied from Api above and changed as follows:
 * - default state is an empty array[], so the map function in jsx is not stuck
 * - an if condition within the useEffect was added, which causes the Api2 to 
 * wait till the condition arrives
 *
 * @param path      [string]  : relative path to baseUrl
 * @param condition [string]  : Api2 will wait till condition is fulfilled
 * @return, Response Data
 */
export function useApi2<T>(
  path: string, condition: string
): [T[] , SetState<T[] >] {
  const [data, setData] = useState<T[]>([]);

  useEffect(() => {

    console.log("condition: ",condition);
    
    if(condition) api("GET", path, setData);
  }, [condition]);

  return [data, setData];
}
/*
 * Useful for calls on events or in conditions
 *
 * @param method [Method], http method
 * @param path [string], relative path to baseUrl
 * @param data [function], callback, gets `response.data` as an argument
 * @param data [object], body data
 */
export function api<T>(
  method: Method,
  path: string,
  callback: (data: T) => void,
  data = {}
): void {
  const url = `${baseUrl}/${path}`;
  console.log("url: ", url);
  
  axios({
    method: method,
    url: `${baseUrl}/${path}`,
    data,
  }).then((response: AxiosResponse<T>) => {
    console.log("res: ", response.data)
    return callback(response.data);
  });
}

/**
 *  Simplified API
 */
export function apiSimple<T>(
  method: Method,
  path: string,
  data = {}
): AxiosPromise<any> {

  const url = `${baseUrl}/${path}`;
  console.log("url: ", url);
  console.log("data: ", data)
  
  return axios({
    method,
    url: `${baseUrl}/${path}`,
    data,
  })
}

