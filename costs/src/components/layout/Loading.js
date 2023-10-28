import loading from '../../img/loading.svg'
import styles from './Loading.module.css'

function Loading(){
    return(
        <section className={styles.loader_conteiner}>
            <img className={styles.loader} src={loading} alt="Loading" />
        </section>
    )
}

export default Loading