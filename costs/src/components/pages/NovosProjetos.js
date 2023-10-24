import React from 'react';
import { useNavigate } from 'react-router-dom'; 

import styles from './NovosProjetos.module.css';
import ProjetoForm from '../project/ProjetoForm';

function NovosProjetos() {

    const history = useNavigate();

    function createPost(project) {

        // Inicializando projeto costs e serviços
        project.cost = 0
        project.services = []

        fetch("http://localhost:5000/projects", {
            method: 'POST',
            headers: {
                'Contant-type': 'application/json',
            },
            body: JSON.stringify(project),
        })
            .then((res) => res.json())
            .then((data) => {
                console.log(data)
                //redirecionar

                history.push('/Projetos', {message: 'Projeto Criado com sucesso!'})
            })
            .catch((err) => console.log(err))

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