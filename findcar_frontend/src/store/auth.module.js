import AuthService from '@/services/auth.services';

const user = JSON.parse(localStorage.getItem('user'));
const tokenInfo = JSON.parse(localStorage.getItem('tokenInfo'));
const initialState = user
    ? { status: { loggedIn: true }, user, tokenInfo}
    : { status: { loggedIn: false }, user: null, tokenInfo };

export const auth = {
    namespaced: true,
    state: initialState,
    actions: {
        getToken({commit}, code) {
            return AuthService.getToken(code).then(
                tokenInfo => {
                    commit('getTokenInfoSuccess', tokenInfo);
                    return Promise.resolve(tokenInfo);
                },
                error => {
                    commit('getTokenOrUserFail');
                    return Promise.reject(error);
                }
            );
        },

        login({ commit }, user) {
            return AuthService.login(user).then(
                user => {
                    commit('loginSuccess', user);
                    return Promise.resolve(user);
                },
                error => {
                    commit('loginFailure');
                    return Promise.reject(error);
                }
            );
        },
        logout({ commit }) {
            AuthService.logout();
            commit('logout');
        },
        register({ commit }, user) {
            return AuthService.register(user).then(
                response => {
                    commit('registerSuccess');
                    return Promise.resolve(response.data);
                },
                error => {
                    commit('registerFailure');
                    return Promise.reject(error);
                }
            );
        }
    },
    mutations: {
        loginSuccess(state, user) {
            state.status.loggedIn = true;
            state.user = user;
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        registerSuccess(state) {
            state.status.loggedIn = false;
        },
        registerFailure(state) {
            state.status.loggedIn = false;
        }
    }
};