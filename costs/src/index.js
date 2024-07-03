import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import axios from 'axios';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import PrivateRoute from './components/auth/PrivateRoute';
import Login from './components/pages/login/Login';
import Home from './components/pages/Home';
import Projetos from './components/pages/projetos';
import Empresa from './components/pages/Empresa';
import Contato from './components/pages/Contato';
import NovosProjetos from './components/pages/NovosProjetos';
import Projeto from './components/pages/Projeto';
import { AuthProvider } from './components/auth/AuthContext';

const AppRouter = () => (
    <BrowserRouter>
        <AuthProvider>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="/" element={<PrivateRoute />}>
                    <Route path="/" element={<App />}>
                        <Route path="/" element={<Home />} />
                        <Route path="/projetos" element={<Projetos />} />
                        <Route path="/empresa" element={<Empresa />} />
                        <Route path="/contato" element={<Contato />} />
                        <Route path="/novosprojetos" element={<NovosProjetos />} />
                        <Route path="/projeto/:id" element={<Projeto />} />
                    </Route>
                </Route>
            </Routes>
        </AuthProvider>
    </BrowserRouter>
);

axios.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        if (error.response && error.response.status === 401) {
            localStorage.removeItem('token');
            window.location.replace('/login');
        }
        return Promise.reject(error);
    }
);

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <AppRouter />
    </React.StrictMode>
);
