import { useRef } from "react";
import rolling from "./assets/rolling.gif";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import usePostFetch from "./hooks/usePostFetch";
import { useDispatch } from "react-redux";
import { setUsername } from "./store/slices/AuthSlice";
import { FaPlane } from "react-icons/fa6";

let Login = () => {
  const usernameRef = useRef();
  const password = useRef();

  const { error, loading, fetch } = usePostFetch();

  const navigate = useNavigate();


  const dispatch = useDispatch();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = await fetch(
      "/login",
      {
        username: usernameRef.current.value,
        password: password.current.value,
      },
      true
    );
    if (data) {
      dispatch(setUsername({username: data.username}))
      localStorage.setItem("JwtToken", data.jwtToken);
      navigate("/");
    }
  };

  return (
    <form
      action=""
      onSubmit={handleSubmit}
      className="w-[90%] max-w-[500px] p-10 bg-bg-light border border-border mt-10 rounded-2xl shadow-2xl text-text"
    >
      <div className="text-text flex items-center gap-2 mb-5">
       <div className="text-3xl font-semibold"> Atomic Seats</div>
        <FaPlane  className="text-4xl"/>
        <div className="text-2xl font-semibold">
          - Login
        </div>
      </div>
      
      <label htmlFor="email">
        <div className="text-2xl mt-2 ">Username: </div>
        <input
          ref={usernameRef}
          required
          className="mt-2 text-gray-600 text-xl p-2 bg-bg-dark  w-[100%] rounded outline-none 
          ring-2 ring-border focus:ring-blue-400 duration-150"
          type="text"
          id="email"
        />
      </label>
      <label htmlFor="password">
        <div className="text-2xl mt-2 ">Password: </div>
        <input
          ref={password}
          required
          className="mt-2 text-gray-600 text-xl p-2 bg-bg-dark border-border w-[100%] rounded   
           ring-2 ring-border outline-none focus:ring-blue-400 duration-150"
          type="password"
          id="password"
        />
      </label>
      <div className="relative mt-10">
        <input
          disabled={loading}
          type="submit"
          className="w-[100%] text-white rounded-xl bg-secondary text-2xl p-2  hover:bg-secondaryHover duration-300 cursor-pointer"
        />
        {error && (
          <div className="text-center text-lg text-red-500">{error}</div>
        )}
        {loading && (
          <img
            className="h-10 w-10 absolute top-1 left-[60%]"
            src={rolling}
            alt=""
          />
        )}
      </div>
      <Link to="/register" className="text-blue-600 text-center block mt-3">
        Register
      </Link>
    </form>
  );
};

export default Login;
