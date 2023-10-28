import { useState, useEffect } from "react"
import { useLocation } from "react-router-dom"

import Message from "../layout/Message"
import Container from "../layout/Container"
import Loading from "../layout/Loading"
import LinkButton from '../layout/LinkButton'
import ProjectCard from "../project/ProjectCard"

import styles from "./Projetos.module.css"



function Projetos() {

    const[projects, setProjects] = useState([])
    const[removeLoading, setRemoveLoading] = useState(false)

    const location = useLocation()
    let message = ''
    if (location.state) {
        message = location.state.message
    }

    useEffect(() => {

        setTimeout(() => {
            fetch('http://localhost:5000/projects', {
            method: 'GET',
            headers: {
                'Content-Type': 'aplication/json'
            }
        })
        .then((res) => res.json())
        .then((data) => {
            console.log(data)
            setProjects(data)
            setRemoveLoading(true)
        })
        .catch((err)  => console.log(err))
        }, 500)

    }, [])

    return (
        <section className={styles.project_container}>
            <section className={styles.title_container}>
                <h1>Meus Projetos</h1>
                <LinkButton to="/NovosProjetos" text="Criar Projeto"/>
            </section>
            {message && <Message type="success" msg={message} />}

            <Container customClass="start">
                {projects.length > 0 && projects.map((projects) => (
                    <ProjectCard 
                    id={projects.id}
                    name={projects.name}
                    budget={projects.budget}
                    category={projects.category.name}
                    key={projects.id}
                    />
                ))}
                {!removeLoading && <Loading/>}
                {removeLoading && projects.length === 0 && (
                    <p>Não ha projetos cadastrados</p>
                )}
            </Container>
        </section>
    )
}

export default Projetos