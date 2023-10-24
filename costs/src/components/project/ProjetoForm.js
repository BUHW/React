import styles from './ProjetoForm.module.css'

function ProjetoForm(){
    return(
        <form className={styles.form}>
            <section>
                <input type="text" placeholder="Digite o nome do Projeto"/>
            </section>
            
            <section>
                <input type="Number" placeholder="Digite o orçamento total"/>
            </section>
           
            <section>
                <select name="categoria_id">
                    <option disabled selected>Selecione a categoria</option>
                </select>
            </section>
            
            <section>
                <input type="submit" value="Criar Projeto"/>
            </section>
        </form>
    )
}

export default ProjetoForm