import styles from "./SubmitButton.module.css";

function SubmitButton({text}) {
    return (
        <section>
            <button className={styles.btn}>{text}</button>
        </section>
    );
}

export default SubmitButton;
