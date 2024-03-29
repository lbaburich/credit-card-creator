import "./App.css";
import { Box, Tab, Tabs } from "@mui/material";
import { useState } from "react";
import CreateCardForm from "./CreateCardForm";
import CheckStatus from "./CheckStatus";

interface TabWrapperProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabWrapper(props: TabWrapperProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && <Box sx={{ p: 3 }}>{children}</Box>}
    </div>
  );
}

function a11yProps(index: number) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

function App() {
  const [value, setValue] = useState(0);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  return (
    <>
      <h1>Card creator</h1>
      <Box sx={{ width: "100%", minHeight: '500px' }}>
        <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
          <Tabs
            value={value}
            onChange={handleChange}
            aria-label="basic tabs example"
          >
            <Tab label="Create card" {...a11yProps(0)} />
            <Tab label="Check status" {...a11yProps(1)} />
          </Tabs>
        </Box>
        <TabWrapper value={value} index={0}>
          <CreateCardForm />
        </TabWrapper>
        <TabWrapper value={value} index={1}>
          <CheckStatus />
        </TabWrapper>
      </Box>
    </>
  );
}

export default App;
