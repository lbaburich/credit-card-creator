import "./App.css";
import { Button, TextField } from "@mui/material";
import { useState } from "react";
import { isValidOIB } from "./oibValidator";

function CheckStatus() {
  const [oib, setOib] = useState<string | null>(null);

  const [status, setStatus] = useState<string | null>(null);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  const handleCheckStatus = async () => {
    setStatus(null);
    setErrorMessage(null);

    if (!oib || !isValidOIB(oib)) {
      setErrorMessage("Not a valid OIB");
      return;
    }

    try {
      const response = await fetch(
        `http://localhost:9090/api/v1/cards/${oib}`
      );
      const resJson = await response.json();
      if (response.ok) {
        setStatus(resJson.status);
      } else {
        setErrorMessage(resJson.errorMessage);
      }
    } catch (er) {
      console.log(er);
    }
  };

  const handleOibChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setOib(e.target.value);
  };

  return (
    <>
      <div className="container">
        <TextField
          label="OIB"
          variant="outlined"
          required
          onChange={handleOibChange}
        />
        <Button
          type="submit"
          variant="contained"
          color="primary"
          onClick={handleCheckStatus}
        >
          Check status
        </Button>
      </div>
      {status && (
        <div style={{color: status === 'PROCESSING' ? 'orange' : 'green', marginTop: '16px', fontSize: '20px'}}>
         Status: {status}
        </div>
      )}
      {errorMessage && <div className="error">{errorMessage}</div>}
    </>
  );
}

export default CheckStatus;
