import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Home from './components/pages/Home'
import Empresa from './components/pages/Empresa'
import Contato from './components/pages/Contato'
import NovosProjetos from './components/pages/NovosProjetos'
import NavBar from './components/layout/NavBar'

import Container from './components/layout/Container'

function App() {
  return (
    <Router>

      <NavBar />
        <Container customClass="min-height">
          <Routes>

            <Route exect path="/" element={<Home />} />
            <Route path="/empresa" element={<Empresa />} />
            <Route path="/contato" element={<Contato />} />
            <Route path="/NovosProjetos" element={<NovosProjetos />} />

          </Routes>

        </Container>
        <p>footer</p>
    </ Router>
  )
}

export default App;
