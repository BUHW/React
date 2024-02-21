import { useState } from 'react'
import './App.css'

function App() {

  const [todos, setTodos] = useState([
    {
      id: 1,
      text: "Criar funcionalidade de sistema",
      category: "trabalho",
      isCompleted: false
    },
    {
      id: 2,
      text: "Criar funcionalidade de sistema",
      category: "Trabalho",
      isCompleted: false
    },
    {
      id: 3,
      text: "Ir a academia",
      category: "Pessoal",
      isCompleted: false
    },
    {
      id: 4,
      text: "EStudar react",
      category: "estudos",
      isCompleted: false
    },
  ]);

  return (
    <>
     <h1>Hello World React</h1>
    </>
  )
}

export default App
