import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import useGetFetch from '../hooks/useGetFetch'
import flightMapImg from "../assets/flightMap.png";

function FlightMap() {
  const location = useLocation();

  const [flightMapData, setFlightMapData] = useState(null)

  const {fetch, loading} = useGetFetch();

  useEffect(()=>{
    const getFlightMap = async()=>{
        const data = await fetch("/getFlightSeats/"+location.state)
        console.log(data)
    }
    getFlightMap()
  },[])

  console.log(location.state);
  return (
    <div className="relative w-full">
      <img src={flightMapImg} className="w-full" alt="" />
      <div
        className=" absolute top-110 left-1/2 
      [transform:translate(-50%,-20%)]   h-400 w-95 flex gap-5 "
      >
        <div className="h-full flex flex-col gap-3  shrink-0 grow">
          
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
        </div>
        <div className="h-full flex flex-col gap-3  shrink-0 grow">
          
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
          <div className="flex justify-around h-13 w-full">
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
            <div className="bg-blue-500 rounded-sm cursor-pointer aspect-square h-full"></div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default FlightMap;
