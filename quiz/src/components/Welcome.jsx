import { useContext } from 'react'
import { QuizContext } from '../context/Quiz'

import Quiz from '../img/quiz.svg' 

function Welcome() {

    const [quizState, dispatch] = useContext(QuizContext)

    return (
        <div id="welcome">
            <h2>Seja bem-vindo</h2>
            <p>Clique para iniciar</p>
            <button onClick={() => dispatch({type: "CHANCE_STATE"})}>
                iniciar
            </button>
            <img src={Quiz} alt="Foto quiz" />
        </div>
    )
}

export default Welcome
