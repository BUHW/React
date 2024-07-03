import axios from "axios"
import { HOST, PORT, HTTP } from "../../../variables"
import { useContext, useState } from "react"
import Input from "../../form/input"
import SubmitButton from "../../form/SubmitButton"
import styles from "./Login.module.css"
import AuthContext from "../../auth/AuthContext"
import { useNavigate } from "react-router-dom"

export default function Login() {

    const [auth, setAuth] = useState({ login: '', password: '' })
    const { login } = useContext(AuthContext)

    const navigate = useNavigate()

    async function handleLogin(e) {
        e.preventDefault();
        try {
            const resp = await axios.post(`${HTTP}://${HOST}:${PORT}/auth/login`, auth)

            console.log(resp.data)

            if (resp.status === 200) {
                const { token } = resp.data
                console.log(token)

                localStorage.removeItem('token')
                localStorage.setItem('token', token)

                login(token)
                navigate('/')
            } else {
                console.log('Erro ao fazer login')
            }

        } catch (error) {
            console.log(error)
        }
    }

    function handleChange(e) {
        setAuth({ ...auth, [e.target.name]: e.target.value })
    }

    function submit(e) {
        e.preventDefault()
        handleLogin(e)
    }

    return (
        <section className={styles.container_login}>
            <form onSubmit={submit}>
                <Input
                    type="text"
                    text="Login"
                    name="login"
                    placeholder="digite o seu login"
                    handleOnChange={handleChange}
                    value={auth.login || ''}
                />

                <Input
                    type="password"
                    text="Senha"
                    name="password"
                    placeholder="Digite a sua senha"
                    handleOnChange={handleChange}
                    value={auth.password || ''}
                />

                <SubmitButton text="Login" />
            </form>
        </section>
    )
}