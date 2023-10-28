import { Link } from 'react-router-dom'

import styles from './ProjectCard.module.css'

import {BsPencil, BsFillTrashFill} from 'react-icons/bs'

function ProjectCard({id, name, budget, category, handleRemove}){

    console.log(category.toLowerCase())

    return(
        <section className={styles.project_card}>
            <h3>{name}</h3>
            <section>
                <p>
                    <span>Orçamento</span> R${budget}
                </p>
                <p className={styles.category_text}>
                    <span className={`${styles[category.toLowerCase()]}`}></span> {category}
                    
                </p>

                <section className={styles.project_card_actions}>
                    <Link to="#">
                        <BsPencil /> Editar
                    </Link>
                    <button>
                        <BsFillTrashFill /> Remover
                    </button>
                </section>
            </section>
        </section>
    )
}

export default ProjectCard