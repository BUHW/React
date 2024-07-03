import styles from './Projeto.module.css'
import { v4 as uuidv4 } from 'uuid'
import { useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import Loading from '../layout/Loading'
import Container from '../layout/Container'
import ProjetoForm from '../project/ProjetoForm'
import ServiceForm from '../service/ServiceForm'
import Message from '../layout/Message'
import ServiceCard from '../service/ServiceCard'
import { HOST, PORT, HTTP, TOKEN } from '../../variables'
import axios from 'axios'

function Projeto() {

    const { id } = useParams()

    const [project, setProject] = useState([])
    const [services, setServices] = useState([])
    const [showProjectForm, setShowProjectForm] = useState(false)
    const [showServiceForm, setShowServiceForm] = useState(false)
    const [message, setMessage] = useState()
    const [type, setType] = useState()


    useEffect(() => {
        axios.get(`${HTTP}://${HOST}:${PORT}/projects/${id}`, {
            headers: {
                'Authorization': `Bearer ${TOKEN}`,
                'Content-Type': 'application/json'
            }
        })
        .then((response) => {
            setProject(response.data);
            setServices(response.data.services);
        })
        .catch((err) => console.log(err));
    }, [id]);

    function editPost(project) {
        setMessage('')
        //budget validation

        if (project.budget < project.cost) {
            setMessage('Orçamento não pode ser menor que o custo do projeto!!')
            setType('inform')
            return false
        }

        axios.patch(`${HTTP}://${HOST}:${PORT}/projects/${project.id}`, project, {
            headers: {
                'Authorization': `Bearer ${TOKEN}`,
                'Content-Type': 'application/json'
            }
        })
        .then((response) => {
            setProject(response.data);
            setShowProjectForm(false);
            setMessage('Projeto atualizado com sucesso');
            setType('success');
        })
        .catch(err => console.log(err));

    }

    function createService(project) {
        setMessage('')
        // Ultimo serviço
        const lastService = project.services[project.services.length - 1]

        lastService.id = uuidv4()

        const lastServiceCost = lastService.cost

        const newCost = parseFloat(project.cost) + parseFloat(lastServiceCost)

        //valor maximo de validação

        if (newCost > parseFloat(project.budget)) {
            setMessage('Orçamento ultrapassado, verifique o valor do serviço')
            setType('error')
            project.services.pop()
            return false
        }

        //adicionar o total do serviço para o custo total do projeto

        project.cost = newCost

        //atualização do projeto

        axios.patch(`${HTTP}://${HOST}:${PORT}/projects/${project.id}`, project, {
            headers: {
                'Authorization': `Bearer ${TOKEN}`,
                'Content-Type': 'application/json'
            }
        })
            .then(() => {
                setShowServiceForm(false)
            })
            .catch(err => console.log(err))
    }

    function removeService(id, cost) {

        const servicesUpdated = project.services.filter(
            (service) => service.id !== id
        )

        const projectUpdated = project

        projectUpdated.services = servicesUpdated
        projectUpdated.cost = parseFloat(projectUpdated.cost) - parseFloat(cost)

        axios.patch(`${HTTP}://${HOST}:${PORT}/projects/${project.id}`, project, {
            headers: {
                'Authorization': `Bearer ${TOKEN}`,
                'Content-Type': 'application/json'
            }
        })
        .then((response) => {
            setShowServiceForm(false);
            console.log(response.data);
        })
        .catch(err => {
            console.error(err);
            setMessage('Erro ao atualizar o projeto');
            setType('error');
        });
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
                            {services.length > 0 &&
                                services.map((service) => (
                                    <ServiceCard
                                        id={service.id}
                                        name={service.name}
                                        cost={service.cost}
                                        description={service.description}
                                        key={service.key}
                                        handleRemove={removeService}
                                    />
                                ))
                            }
                            {services.length === 0 && <p>Não há serviços cadastrados.</p>

                            }
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