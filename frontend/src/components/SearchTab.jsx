import React, { forwardRef } from "react";
import { CgArrowsExchange } from "react-icons/cg";
import { FaMagnifyingGlass } from "react-icons/fa6";

const SearchTab = forwardRef(({ getFlights }, ref) => {
  {
    const cities = ["Delhi", "Mumbai", "Bangalore", "Hyderabad", "Chennai"];

    const airLines = [
      "Air India",
      "Spice jet",
      "Indigo",
      "Akasa Air",
      "Air India Express",
    ];

    const switchCities = () => {
      const selects = document.querySelectorAll("select");
      const temp = selects[0].value;
      selects[0].value = selects[1].value;
      selects[1].value = temp;
    };

    const handleSubmit = (e) => {
      e.preventDefault();
      getFlights();
    };

    return (
      <form
        onSubmit={handleSubmit}
        className=" p-2 shrink-0 text-text bg-bg w-full flex-wrap flex items-center text-3xl gap-6 justify-center"
      >
        <div className="relative">
          <div className="absolute text-sm left-3 top-1 text-secondary font-semibold">
            From
          </div>
          <select
            className="  p-2
          pr-3 outline-none pt-4 bg-bg-dark rounded-lg "
            name=""
            id=""
            defaultValue={ref.current.from}
            onChange={(e) => (ref.current.from = e.target.value)}
          >
            {cities.map((city) => (
              <option key={city} value={city}>
                {city}
              </option>
            ))}
          </select>
        </div>
        <CgArrowsExchange
          onClick={switchCities}
          className="text-5xl  mx-3 cursor-pointer hover:scale-110 duration-300"
        />
        <div className="relative">
          <div className="absolute text-sm left-3 top-1 text-secondary font-semibold">
            To
          </div>
          <select
            className="  p-2 pr-3 outline-none pt-4 bg-bg-dark rounded-lg "
            name=""
            id=""
            defaultValue={ref.current.to}
            onChange={(e) => (ref.current.to = e.target.value)}
          >
            {cities.map((city) => (
              <option key={city} value={city}>
                {city}
              </option>
            ))}
          </select>
        </div>

        <div className="relative">
          <div className="absolute top-1 left-3 font-semibold text-secondary text-sm">
            Departure
          </div>
          <input
            type="date"
            defaultValue={ref.current.date}
            onChange={(e) => (ref.current.date = e.target.value)}
            className="p-2  rounded-lg pt-6 bg-bg-dark"
          />
        </div>
        <div className="relative">
          <div className="absolute left-3 top-1 text-secondary font-semibold text-sm">
            Air Line
          </div>
          <select
            defaultValue={ref.current.airlineName}
            onChange={(e) => {
              if (e.target.value === "All") {
                ref.current.airlineName = null;
              } else {
                ref.current.airlineName = e.target.value;
              }
            }}
            className="pt-4  p-2
        pr-3 outline-none bg-bg-dark rounded-lg "
            name=""
            id=""
          >
            <option value={null}>All</option>
            {airLines.map((airLine) => (
              <option key={airLine} value={airLine}>
                {airLine}
              </option>
            ))}
          </select>
        </div>
        <button>
          <FaMagnifyingGlass className="mx-3 text-3xl hover:scale-110 duration-300 cursor-pointer" />
        </button>
      </form>
    );
  }
});

export default SearchTab;
