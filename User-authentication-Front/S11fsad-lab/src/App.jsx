import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "./components/Dashboard";
import LocalUserList from "./components/LocalUserList";
import ApiUserList from "./components/ApiUserList";
function App() {
return (
<BrowserRouter>
<Routes>
<Route path="/" element={<Dashboard />} />
<Route path="/local" element={<LocalUserList />} />
<Route path="/api" element={<ApiUserList />} />
</Routes>
</BrowserRouter>
);
}
export default App;