import { useState } from "react"; 
import axios from "axios"; 
function AddStudent({ refreshStudents, editingStudent, setEditingStudent }) { 
  const [name, setName] = useState(""); 
  const [email, setEmail] = useState(""); 
  const [course, setCourse] = useState(""); 
  const handleSubmit = async (e) => { 
    e.preventDefault(); 
    const student = { name, email, course }; 
    if (editingStudent) { 
      await axios.put(`http://localhost:8085/students/${editingStudent.id}`, student); 
      setEditingStudent(null); 
    } else { 
      await axios.post("http://localhost:8085/students", student); 
    } 
    setName(""); 
    setEmail(""); 
    setCourse(""); 
    refreshStudents(); 
  }; 
  return ( 
    <form onSubmit={handleSubmit}> 
      <h3>{editingStudent ? "Update Student" : "Add Student"}</h3> 
      <input 
        placeholder="Name" 
        value={name} 
        onChange={(e) => setName(e.target.value)} 
      /> 
      <input 
        placeholder="Email" 
        value={email} 
        onChange={(e) => setEmail(e.target.value)} 
      /> 
      <input 
        placeholder="Course" 
        value={course} 
        onChange={(e) => setCourse(e.target.value)} 
      /> 
      <button type="submit"> 
        {editingStudent ? "Update" : "Add"} 
      </button> 
    </form> 
  ); 
} 
export default AddStudent; 