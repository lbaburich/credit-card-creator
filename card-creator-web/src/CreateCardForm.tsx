import "./App.css";
import { Button, TextField } from "@mui/material";
import { useState } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import { isValidOIB } from "./oibValidator";

type FormDataType = {
  firstName: string;
  lastName: string;
  status: "PROCESSING";
  oib: string;
};

function CreateCardForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormDataType>();

  const [isSuccess, setIsSuccess] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  const onSubmit: SubmitHandler<FormDataType> = async (data: FormDataType) => {
    const finalData = { ...data, status: "PROCESSING" };
    setIsSuccess(false);
    setErrorMessage(null);

    if (!isValidOIB(data.oib)) {
      setErrorMessage("Not a valid OIB");
      return;
    }

    try {
      const response = await fetch("http://localhost:8081/api/v1/producer/cards", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(finalData),
      });
      if (response.ok) {
        setIsSuccess(true);
      } else {
        const resJson = await response.json();
        setErrorMessage(resJson.errorMessage);
        throw new Error("Failed to submit form data");
      }
    } catch (er) {
      console.log(er);
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="container">
          <TextField
            {...register("oib", { required: true, pattern: /^[0-9]{11}$/ })}
            name="oib"
            label="OIB"
            variant="outlined"
            error={!!errors.oib}
            helperText={errors.oib ? "This field must contain 11 numbers" : ""}
            required
          />
          <TextField
            {...register("firstName", { required: true })}
            name="firstName"
            label="First name"
            variant="outlined"
            error={!!errors.firstName}
            helperText={errors.firstName ? "This field is required" : ""}
            required
          />
          <TextField
            {...register("lastName", { required: true })}
            name="lastName"
            label="Last name"
            variant="outlined"
            error={!!errors.lastName}
            helperText={errors.lastName ? "This field is required" : ""}
            required
          />
          <Button type="submit" variant="contained" color="primary">
            Submit
          </Button>
        </div>
      </form>
      {isSuccess && (
        <div className="success">
          Your card creation request is being processed. Check status in 2
          minutes.
        </div>
      )}
      {errorMessage && <div className="error">{errorMessage}</div>}
    </>
  );
}

export default CreateCardForm;
