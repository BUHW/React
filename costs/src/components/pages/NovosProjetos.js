import styles from './NovosProjetos.module.css'

import ProjetoForm from '../project/ProjetoForm'

function NovosProjetos(){
    return(
        <section className={styles.novoprojeto_conteiner}>
            <h1>Criar Projeto</h1>
            <p>Crie seu projeto para depois adicionar os serviços</p>
            <ProjetoForm />

        </section>
    )
}

export default NovosProjetos