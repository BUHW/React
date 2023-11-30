import Quiz from '../img/quiz.svg' 

function Welcome() {
    return (
        <div id="welcome">
            <h2>Seja bem-vindo</h2>
            <p>Clique para iniciar</p>
            <button>
                iniciar
            </button>
            <img src={Quiz} alt="Foto quiz" />
        </div>
    )
}

export default Welcome
