async function exibirPosts(){
	try{
		const response = await fetch("http://localhost:8080/Posts");
		const postas = await response.json();

		const poster = document.querySelector('.Posts');

		postas.forEach(post => {

			const PostContainer = document.createElement("div");
			PostContainer.classList.add("post_Container");
			const authorImg = document.createElement('img');
			authorImg.classList.add('authorImg');

			const authorName = document.createElement('h3');
				authorName.classList.add('AuthorName');
			authorName.textContent = post.author;

			const postContent = document.createElement('div');
				postContent.classList.add('post_Content');

			const postTextContent = document.createElement('p');
				postTextContent.classList.add('post_text_content');

			postTextContent.textContent = post.content;

			const divFeedBack = document.createElement('div');
				divFeedBack.classList.add('Post_Feedback');

			const daora = document.createElement('img');
				daora.classList.add('daora');

			const odiei = document.createElement('img');
				odiei.classList.add('odiei');


			const comments = document.createElement('img');
				comments.classList.add('comments');


			const commentsQuantity = document.createElement('p');
				commentsQuantity.classList.add('commentQuantity');

				commentsQuantity.textContent = post.commentsQuantity;
				poster.appendChild(PostContainer);
				PostContainer.appendChild(authorImg);
				PostContainer.appendChild(authorName);
				PostContainer.appendChild(postContent);
				
				postContent.appendChild(postTextContent);

				PostContainer.appendChild(divFeedBack);

				divFeedBack.appendChild(daora);
				divFeedBack.appendChild(odiei);
				divFeedBack.appendChild(comments);
				divFeedBack.appendChild(commentsQuantity);
				

		});
	}catch(error){
		console.error('Erro construir post:', error);
	}
}

document.addEventListener('DOMContentLoaded', exibirPosts);