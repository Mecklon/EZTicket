import React from "react";
import { MdLogout } from "react-icons/md";
import { useDispatch } from "react-redux";
import { setUsername } from "../store/slices/AuthSlice";

function LogoutButton() {
  const dispatch = useDispatch();

  const logout = () => {
    dispatch(setUsername({username: null}));
  };

  return (
    <div onClick={ logout} className="fixed right-2 top-3 text-2xl flex gap-2 items-center p-3 rounded-xl cursor-pointer hover:bg-bg-dark duration-300">
      Logout <MdLogout />
    </div>
  );
}

export default LogoutButton;
