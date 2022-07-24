import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Route, Switch } from 'react-router-dom';
import { bindActionCreators } from "redux";
import OAuth2RedirectHandler from '../components/Login/oauth2/OAuth2RedirectHandler';
import LoginContainer from '../containers/LoginContainer';
import { EditorPage, NotFoundPage, PostListPage, PostPage } from '../pages';
import AboutPage from '../pages/About';
import * as authActions from '../store/modules/auth';

class App extends Component {

  componentDidMount() {
    const { AuthActions } = this.props;    
    AuthActions.getUser();
  }

  render() {
    return (
      <div>
        <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}/>      
        <Route path="/login" component={LoginContainer} />       
        <Switch>
          <Route path="/about" component={AboutPage} />
          <Route path="/pages/:page" component={PostListPage} />
          <Route path="/posts/:id" component={PostPage} />
          <Route path="/editor/:id?" component={EditorPage} />          
          <Route path="/" component={PostListPage} />
          <Route component={NotFoundPage} />
        </Switch>       
      </div>
    );
  }
};


export default connect(
  state => ({   
  }),
  dispatch => ({
    AuthActions: bindActionCreators(authActions, dispatch)
  })
)(App);