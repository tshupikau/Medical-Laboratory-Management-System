import './App.css'
import Patient from './pages/PatientPage';
import PatientsPage from './pages/PatientsPage'
import { Routes, Route } from "react-router-dom";

function App() {

    return (
    <Routes>
      <Route path="/" element={<PatientsPage />} />
      <Route path="/patient/:id" element={<Patient />} />
    </Routes>
  );
}

export default App
