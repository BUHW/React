import styles from './Projeto.module.css'

import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import Loading from '../layout/Loading'
import Container from '../layout/Container'
import ProjetoForm from '../project/ProjetoForm'
import ServiceForm from '../service/ServiceForm'
import Message from '../layout/Message'

function Projeto() {

    const { id } = useParams()

    const [project, setProject] = useState([])
    const [showProjectForm, setShowProjectForm] = useState(false)
    const [showServiceForm, setShowServiceForm] = useState(false)
    const [message, setMessage] = useState()
    const [type, setType] = useState()


    useEffect(() => {

        setTimeout(() => {

            fetch(`http://localhost:5000/projects/${id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(res => res.json())
                .then((data) => {
                    setProject(data)
                })
                .catch(err => console.log(err))

        }, 500)

    }, [id])

    function editPost(project) {
        setMessage('')
        //budget validation

        if (project.budget < project.cost) {
            setMessage('Orçamento não pode ser menor que o custo do projeto!!')
            setType('inform')
            return false
        }

        fetch(`http://localhost:5000/projects/${project.id}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(project),
        })
            .then((res) => res.json())
            .then((data) => {

                setProject(data)
                setShowProjectForm(false)
                setMessage('Projeto atualizado com sucesso')
                setType('success')

            })
            .catch(err => console.log(err))

    }

    function createService(){

        // Ultimo serviço

    }

    function toggleProjectForm() {
        setShowProjectForm(!showProjectForm)
    }
    
    function toggleServiceForm() {
        setShowServiceForm(!showServiceForm)
    }

    return (
        <>
            {project.name ? (
                <section className={styles.project_details}>
                    <Container customClass="column">
                        {message && <Message type={type} msg={message} />}
                        <section className={styles.details_container}>
                            <h1>Projeto: {project.name}</h1>
                            <button className={styles.btn} onClick={toggleProjectForm}>
                                {!showProjectForm ? 'Editar Projeto' : 'Fechar Projeto'}
                            </button>
                            {!showProjectForm ? (
                                <section className={styles.project_info}>
                                    <p>
                                        <span>Categoria: </span> {project.category.name}
                                    </p>
                                    <p>
                                        <span>Total de Orçamento: </span> R$ {project.budget}
                                    </p>
                                    <p>
                                        <span>Total utilizado: </span> R$ {project.cost}
                                    </p>

                                </section>
                            ) : (
                                <section className={styles.project_info}>
                                    <ProjetoForm handleSubmit={editPost} btnText="Concluir edição"
                                    projectData={project} />
                                </section>
                            )}
                        </section>
                        <section className={styles.service_form_container}>
                            <h2>Adicione um serviço</h2>
                            <button className={styles.btn} onClick={toggleServiceForm}>
                                {!showServiceForm ? 'Adicionar serviço' : 'Fechar'}
                            </button>
                            <section className={styles.project_info}>
                                {showServiceForm && (<ServiceForm 
                                    handleSubmit={createService}
                                    btnText="Adicionar serviço"
                                    projectData={project}
                                />)}
                            </section>
                        </section>
                        <h2>Serviços</h2>
                        <Container customClass="start">
                            <p>Itens de serviços</p>
                        </Container>
                    </Container>
                </section>
            ) : (
                <Loading />
            )}
        </>
    )
}

export default Projeto