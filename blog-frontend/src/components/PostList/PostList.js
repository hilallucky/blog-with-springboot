import {
  Card,
  Grid
} from '@mui/material';
import classNames from 'classnames/bind';
import React, { Fragment } from 'react';
import { Link } from 'react-router-dom';
import { Button } from 'reactstrap';
import PostPreview from '../../components/PostList/PostPreview';
import styles from './PostList.scss';

const cx = classNames.bind(styles);

const PostList = ({ isAuthenticated, posts }) => {
  if (posts === undefined) {
    return null;
  }

  const postList = posts.map((post) => {

    let postBody = post.get("body");
    let isReadMore = false;
    if (postBody.length > 200) {
      isReadMore = true;
    }

    return (

        <Grid container>

          <Grid item xs={6} sx={{ pb: 1 }}>

            {post.get("id") % 2 === 1 && (
              <div key={post.get("id")}>
                <div className={cx('post')}>
                  <div style={{ marginLeft: '20px' }}>
                    <PostPreview post={post} />
                    {isReadMore &&
                      <Button
                        className={cx('more-btn')}
                        color='primary'
                        size='sm'
                        tag={Link}
                        to={"/posts/" + post.get("id")}>
              Read More >>
                      </Button>}
                  </div>
                </div>
                <hr />
              </div>
            )}
          </Grid>




          <Grid item xs={6} sx={{ pb: 1 }}>
            
            {post.get("id") % 2 === 0 && (
              <div key={post.get("id")}>
                <div className={cx('post')}>
                  <div style={{ marginLeft: '20px' }}>
                    <PostPreview post={post} />
                    {isReadMore &&
                      <Button
                        className={cx('more-btn')}
                        color='primary'
                        size='sm'
                        tag={Link}
                        to={"/posts/" + post.get("id")}>
              Read More >>
                      </Button>}
                  </div>
                </div>
                <hr />
              </div>
            )}
          </Grid>


        </Grid>
    )
  });

  return (
    <Fragment>
    <div>
    {isAuthenticated && <Button color='info' tag={Link} to={"/editor"}>NEW POST</Button>}
    </div>
      {postList}
    </Fragment>
  )
};

export default PostList;