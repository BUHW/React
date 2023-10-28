//  Componentes para navegar entre paginas

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Home from './components/pages/Home'
import Empresa from './components/pages/Empresa'
import Contato from './components/pages/Contato'
import NovosProjetos from './components/pages/NovosProjetos'
import Projetos from './components/pages/projetos'
import Projeto from './components/pages/Projeto'

// Componente de layout 
import Container from './components/layout/Container'
import Footer from './components/layout/Footer'
import NavBar from './components/layout/NavBar'


function App() {
  return (
    <Router>

      <NavBar />
        <Container customClass="min-height">
          <Routes>

            <Route exect path="/" element={<Home />} />
            <Route path="/Projetos" element={<Projetos />} />
            <Route path="/empresa" element={<Empresa />} />
            <Route path="/contato" element={<Contato />} />
            <Route path="/NovosProjetos" element={<NovosProjetos />} />
            <Route path="/Projeto/:id" element={<Projeto />} />

          </Routes>

        </Container>
        
        <Footer/>
    </ Router>
  )
}

export default App;
