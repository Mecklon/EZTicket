import { useEffect, useRef, useState } from "react";
import "./index.css";
import { Link, useNavigate } from "react-router-dom";
import rolling from "./assets/rolling.gif";
import usePostFetch from "./hooks/usePostFetch";
import { useDispatch } from "react-redux";
import { setUsername } from "./store/slices/AuthSlice";
import { FaPlane } from "react-icons/fa6";


function Signup() {
  const dispatch = useDispatch();
  const { error, loading, fetch } = usePostFetch();

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = await fetch(
      "/signup",
      {
        username: name.current.value,
        email: email.current.value,
        password: password.current.value,
      },
      true
    );

    if (data) {
      localStorage.setItem("JwtToken", data.jwtToken);
      dispatch(setUsername({ username: data.username }));
      navigate("/");
    }
  };

  const name = useRef();
  const email = useRef();
  const password = useRef();

  return (
    <form
      onSubmit={handleSubmit}
      action=""
      className="w-[90%] max-w-[500px] p-10 bg-bg-light border border-border mt-10 rounded-2xl shadow-2xl"
    >
      <div className="text-text flex items-center gap-2 mb-5">
       <div className="text-3xl font-semibold"> Atomic Seats</div>
        <FaPlane  className="text-4xl"/>
        <div className="text-2xl font-semibold">
          - Sign Up
        </div>
      </div>
      <label htmlFor="name">
        <div className="text-2xl text-text">Username: </div>
        <input
          ref={name}
          required
          className="mt-2 text-gray-600 text-xl p-2 bg-bg-dark w-[100%] rounded outline-none focus:ring-blue-400 ring-2 ring-border duration-150"
          type="text"
          id="name"
        />
      </label>
      <label htmlFor="email">
        <div className="text-2xl mt-2 text-text">Email: </div>
        <input
          ref={email}
          required
          className="mt-2 text-gray-600 text-xl p-2 bg-bg-dark w-[100%] rounded outline-none focus:ring-blue-400 ring-2 ring-border duration-150"
          type="text"
          id="email"
        />
      </label>
      <label htmlFor="password">
        <div className="text-2xl mt-2 text-text">Password: </div>
        <input
          ref={password}
          required
          className="mt-2 text-gray-600 text-xl p-2 bg-bg-dark w-[100%] rounded outline-none focus:ring-blue-400 ring-2 ring-border duration-150"
          type="password"
          id="password"
        />
      </label>
      <div className="relative mt-10">
        <input
          disabled={loading}
          type="submit"
          className="w-[100%] rounded-xl bg-blue-800 text-white text-2xl p-2  hover:bg-blue-900 duration-300 cursor-pointer"
        />
        {loading && (
          <img
            className="h-10 w-10 absolute top-1 left-[60%]"
            src={rolling}
            alt=""
          />
        )}
      </div>
      {error && <div className="text-center text-lg text-red-500">{error}</div>}

      <Link to="/login" className="text-blue-600 text-center block mt-3">
        Login
      </Link>
    </form>
  );
}

export default Signup;
