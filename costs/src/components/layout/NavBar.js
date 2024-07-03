import { Link, useNavigate } from 'react-router-dom'

import Container from './Container'

import styles from './NavBar.module.css'

import logo from '../../img/costs_logo.png'
import { useContext } from 'react'
import AuthContext from '../auth/AuthContext'

function NavBar() {

    const { logout } = useContext(AuthContext)
    const navigate = useNavigate()

    function handleLogout() {
        logout();
        navigate('/login')
    }

    return (
        <nav className={styles.navbar}>
            <Container>
                <Link to="/">
                    <img src={logo} alt='Costs' />
                </Link>
                <ul className={styles.list}>

                    <li className={styles.item}>
                        <Link to="/">Home</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/Projetos">Projeto</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/empresa">Empresa</Link>
                    </li>
                    <li className={styles.item}>
                        <Link to="/contato">Contatos</Link>
                    </li>
                    <li className={styles.item} onClick={handleLogout}>
                        <Link to="/login">Login</Link>
                    </li>

                </ul>
            </Container>
        </nav>
    )
}

export default NavBar