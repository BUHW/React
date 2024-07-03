import { useContext } from 'react';
import AuthContext from './AuthContext';

const UseAuth = () => {
    const authContext = useContext(AuthContext);
    if (!authContext) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return authContext;
};

export default UseAuth;
