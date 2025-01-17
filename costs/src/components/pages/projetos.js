import { useState, useEffect } from "react"
import { useLocation } from "react-router-dom"

import Message from "../layout/Message"
import Container from "../layout/Container"
import Loading from "../layout/Loading"
import LinkButton from '../layout/LinkButton'
import ProjectCard from "../project/ProjectCard"
import { HOST, PORT, HTTP } from '../../variables'
import axios from "axios"

import styles from "./Projetos.module.css"

function Projetos() {

    const [projects, setProjects] = useState([])
    const [removeLoading, setRemoveLoading] = useState(false)
    const [projectMessage, setProjectsMessage] = useState('')

    const location = useLocation()
    let message = ''
    if (location.state) {
        message = location.state.message
    }

    useEffect(() => {
        axios.get(`${HTTP}://${HOST}:${PORT}/projects`)
        .then(res => {
            console.log(res.data)
            setProjects(res.data)
            setRemoveLoading(true)
        })
        .catch(err => console.log(err))
    }, [])

    function removeProject(id){
        axios.delete(`${HTTP}://${HOST}:${PORT}/projects/${id}`)
        .then(() => {
            setProjects(projects.filter((project) => project.id !== id))

            // message
            setProjectsMessage('Projeto removido com sucesso!')
        })
        .catch(err => console.log(err))
    }

    return (
        <section className={styles.project_container}>
            <section className={styles.title_container}>
                <h1>Meus Projetos</h1>
                <LinkButton to="/NovosProjetos" text="Criar Projeto" />
            </section>
            {message && <Message type="success" msg={message} />}
            {projectMessage && <Message type="error" msg={projectMessage} />}

            <Container customClass="start">
                {projects.length > 0 && projects.map((project) => (
                    <ProjectCard
                        id={project.id}
                        name={project.name}
                        budget={project.budget}
                        category={project.category ? project.category.name : "Categoria Desconhecida"}
                        key={project.id}
                        handleRemove={removeProject}
                    />
                ))}

                {!removeLoading && <Loading />}
                {removeLoading && projects.length === 0 && (
                    <p>Não ha projetos cadastrados</p>
                )}
            </Container>
        </section>
    )
}

export default Projetos