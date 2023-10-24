import {Link} from 'react-router-dom'

function NavBar() {
    return (
        <section>
            <ul>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/contato">Contatos</Link></li>
                <li><Link to="/empresa">Empresa</Link></li>
                <li><Link to="/NovosProjetos">Novos Projeto</Link></li>
            </ul>
        </section>
    )
}

export default NavBar