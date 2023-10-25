import styles from "./input.module.css";

function input({ type, text, name, placeholder, handleOnChange, value }) {
    return (
        <section className={styles.form_control}>
            <label htmlFor={name}>{text}:</label>
            <input
                type={type}
                name={name}
                id={name}
                placeholder={placeholder}
                onChange={handleOnChange}
                value={value}
                
            />
        </section>
    );
}

export default input;
