import { Outlet } from 'react-router-dom';
import NavBar from './components/layout/NavBar'
import Footer from './components/layout/Footer'
import Container from './components/layout/Container';

function App() {
  return (
    <>
      <NavBar />
      <Container customClass="min-height">
        <Outlet />
      </Container>
      <Footer />
    </>
  )
}

export default App;
