import React, { forwardRef } from "react";
import { MdLogout } from "react-icons/md";
import { useNavigate } from "react-router-dom";

const FlightDetail = forwardRef(({ flight }, ref) => {
  const getImageSource = () => {
    if (flight.airlineName === "Air India") {
      return "url(https://imgak.mmtcdn.com/flights/assets/media/dt/common/icons/AI.png?v=20)";
    } else if (flight.airlineName === "Akasa Air") {
      return "url(https://imgak.mmtcdn.com/flights/assets/media/dt/common/icons/QP.png?v=20)";
    } else if (flight.airlineName === "Indigo") {
      return "url(https://imgak.mmtcdn.com/flights/assets/media/dt/common/icons/6E.png?v=20)";
    } else if (flight.airlineName === "Spice jet") {
      return "url(https://imgak.mmtcdn.com/flights/assets/media/dt/common/icons/SG.png?v=20)";
    } else {
      return "url(https://imgak.mmtcdn.com/flights/assets/media/dt/common/icons/IX.png?v=20)";
    }
  };

  const navigate = useNavigate();

  const getDifference = (departure, arrival) => {
    const d1 = new Date(departure);
    const d2 = new Date(arrival);

    const diffMs = d2 - d1;

    const totalMinutes = Math.floor(diffMs / (1000 * 60));
    const hours = Math.floor(totalMinutes / 60);
    const minutes = totalMinutes % 60;

    return `${hours} h ${minutes} m`;
  };

  return (
    <div
    onClick={()=>{
        navigate("/Seats",{state:flight.id})
    }}
      ref={ref}
      className="cursor-pointer flex gap-10 text-text justify-between shrink-0 max-w-[900px] w-full rounded-lg p-3  h-30 bg-bg-dark"
    >
      <div className="flex gap-2">
        <div
          className="aspect-square h-10 bg-no-repeat bg-contain"
          style={{
            backgroundImage: getImageSource(),
          }}
        ></div>
        <div className="font-semibold"> {flight.airlineName}</div>
      </div>
      <div className="self-center">
        <div className="text-2xl font-bold">
          {flight.departure.substring(12, 16)}
        </div>
        <div>{flight.from}</div>
      </div>
      <div className="grow self-center">
        <div className="text-center">{getDifference(flight.departure, flight.arrival)}</div>
        <div className="h-1 w-full bg-black"></div>
      </div>
      <div className="self-center">
        <div className="text-2xl font-bold">
          {flight.arrival.substring(12, 16)}
        </div>
        <div>{flight.to}</div>
      </div>
      <div className="text-xl font-semibold">{flight.price}$</div>
    </div>
  );
});

export default FlightDetail;
