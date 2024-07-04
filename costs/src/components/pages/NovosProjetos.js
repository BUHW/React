
import { useNavigate } from 'react-router-dom';
import { HOST, PORT, HTTP } from '../../variables'

import styles from './NovosProjetos.module.css';
import ProjetoForm from '../project/ProjetoForm';
import axios from 'axios';

function NovosProjetos() {

    const history = useNavigate();

    function createPost(project) {
        // Inicializando projeto costs e serviços
        project.cost = 0
        project.services = []

        axios.post(`${HTTP}://${HOST}:${PORT}/projects`, project)
            .then(() => {
                history('/Projetos', { state: { message: 'Projeto Criado com sucesso!' } });
            })
            .catch(err => console.log(err))


    }


    return (
        <section className={styles.novoprojeto_conteiner}>
            <h1>Criar Projeto</h1>
            <p>Crie seu projeto para depois adicionar os serviços</p>
            <ProjetoForm handleSubmit={createPost} btnText="Criar Projeto" />

        </section>
    )
}

export default NovosProjetos