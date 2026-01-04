import React, { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import SearchTab from "./SearchTab";
import FlightDetail from "./FlightDetail";
import LogoutButton from "./LogoutButton";
import usePostFetch from "../hooks/usePostFetch";
import { ImGift } from "react-icons/im";
import image from '../assets/blackRolling.png'

function App() {
  const ref = useRef();
  const hasMore = useRef(true);

  const [flights, setFlights] = useState([]);

  const { fetch, loading } = usePostFetch();

  const now = () => {
    const dateObj = new Date();

    let day = dateObj.getDate();
    let month = dateObj.getMonth() + 1;
    const year = dateObj.getFullYear();

    if (month < 9) month = "0" + month;
    if (day < 9) day = "0" + day;

    return year + "-" + month + "-" + day;
  };

  const filter = useRef({
    from: "Bangalore",
    to: "Delhi",
    airlineName: null,
    date: now(),
    pageNo: 0,
  });

  const getFlights = async () => {
    hasMore.current = true;
    filter.current.pageNo = 0;
    const data = await fetch("/getFlights", filter.current);
    if (data.length < 20) hasMore.current = false;
    setFlights(data);
  };

  useEffect(() => {
    getFlights();
  }, []);

  useEffect(() => {
    const observer = new IntersectionObserver(async (entries) => {
      const entry = entries[0];
      if (!entry.isIntersecting || !hasMore.current) return;
      filter.current.pageNo++;
      const data = await fetch("/getFlights", filter.current);
      if (data.length < 20) hasMore.current = false;
      setFlights((prev) => [...prev, ...data]);
    });

    if (ref.current) {
      observer.observe(ref.current);
    }

    return () => {
      observer.disconnect();
    };
  }, [flights]);

  return (
    <div className="w-full flex flex-col h-full">
      <LogoutButton />
      <SearchTab ref={filter} getFlights={getFlights} />
      <div className="flex flex-col items-center mt-5 gap-3 grow min-h-0 overflow-auto pb-10">
        {flights.map((flight, index) => {
          if (index === flights.length - 1) {
            return <FlightDetail flight={flight} ref={ref} key={flight.id} />;
          } else {
            return <FlightDetail flight={flight} key={flight.id} />;
          }
        })}
        {loading && <img src={image}/>}
      </div>
    </div>
  );
}

export default App;
