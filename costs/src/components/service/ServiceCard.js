import styles from '../project/ProjectCard.module.css'

import {BsFillTrashFill} from 'react-icons/bs'

function ServiceCard({id, name, cost, description, handleRemove}){

    const remove = (e) => {
        e.preventDefault()
        handleRemove(id , cost)
    }

    return (
        <section className={styles.project_card}>
            <h1>{name}</h1>
            <p>
                <span>Custo total: </span> R${cost}
            </p>
            <p>
                {description}
            </p>

            <section className={styles.project_card_actions}>
                <button onClick={remove}>
                    <BsFillTrashFill />
                    Remover
                </button>
            </section>
        </section>
    )
}

export default ServiceCard